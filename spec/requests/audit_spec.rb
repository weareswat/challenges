# frozen_string_literal: true

require 'rails_helper'
require 'pry'
require 'factory_bot'

RSpec.describe Audit, type: :request do
  let!(:first_audit) { create(:audit, :first_change) }
  let!(:second_audit) { create(:audit, :second_change) }
  let!(:third_audit) { create(:audit, :third_change) }

  describe 'POST audits#create' do
    context 'when a payload is sent along with the request' do
      let(:payload) do
        {
          "_id": 1,
          "name": "Bruce Willis",
          "address": {
            "street": "Nakatomi Plaza"
          }
        }
      end

      before do
        DatabaseCleaner.clean
        post '/audits', params: payload.to_json, headers: { 'CONTENT_TYPE' => 'application/json' }
      end

      it 'returns a status 200 and success message' do
        parsed_response = JSON.parse(response.body).deep_symbolize_keys

        expect(parsed_response).to include(
          { status: 200, success: 'Audit created!' }
        )
      end
    end

    context 'when an empty payload is sent along with the request' do
      before do
        post '/audits', params: {}, headers: { 'CONTENT_TYPE' => 'application/json' }
      end

      it 'renders a response with RuntimeError exception and InvalidObject error' do
        parsed_response = JSON.parse(response.body).deep_symbolize_keys

        expect(parsed_response).to include(
          { status: 'unprocessable_entity', error: 'Audit creation failed!' }
        )
      end
    end

    context 'when an invalid object in payload is sent along with the request' do
      before do
        post '/audits', params: { invalid_request: {} }
      end

      it 'renders an error object with status, exception and error' do
        parsed_response = JSON.parse(response.body).deep_symbolize_keys

        expect(parsed_response).to include(
          { status: 422, exception: 'RuntimeError', error: 'InvalidObject' }
        )
      end
    end
  end

  describe 'GET audits#index' do
    let(:start_date) { Date.yesterday.to_s }
    let(:end_date) { Date.today.to_s }

    context 'when request includes params' do
      before do
        get "/audits?start_date=#{start_date}&end_date=#{end_date}"
      end

      it 'renders the user/field changes in specified timespan' do
        parsed_response = JSON.parse(response.body).map(&:deep_symbolize_keys)

        expect(parsed_response).to include(
          field: an_instance_of(String),
          new: an_instance_of(String),
          old: an_instance_of(String)
        )
      end
    end

    context 'when request does not include params' do
      before do
        get '/audits'
      end

      it 'renders all user/field changes' do
        parsed_response = JSON.parse(response.body).map(&:deep_symbolize_keys)
        expect(parsed_response).to include(
          field: an_instance_of(String),
          new: an_instance_of(String),
          old: an_instance_of(String)
        )
      end
    end
  end
end
