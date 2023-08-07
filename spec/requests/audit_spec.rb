# frozen_string_literal: true

require 'rails_helper'
require 'pry'

RSpec.describe Audit, type: :request do
  let(:payload) do
    {
      'audit': {
        'model': 'User',
        'old': {
          '_id': 1,
          'name': 'Bruce Norries',
          'address': {
            'street': 'Some street'
          }
        },
        'new': {
          '_id': 1,
          'name': 'Bruce Willis',
          'address': {
            'street': 'Nakatomi Plaza'
          }
        }
      }
    }
  end

  describe 'POST audits#create' do
    context 'when request is made with payload' do
      it 'returns success message' do
        post '/audits', params: payload
        parsed_response = JSON.parse(response.body)
        expect(parsed_response).to include(
          { 'status' => 200, 'success' => 'Audit created!' }
        )
      end
    end

    context 'when request has no payload' do
      it 'should render recipes#show template with date of today' do
        post '/audits'
        parsed_response = JSON.parse(response.body)
        expect(parsed_response).to include(
          { 'status' => 422, 'error' => 'Please provide the necessary parameters!' }
        )
      end
    end
  end

  describe 'GET audits#show' do
    let(:audit) { Audit.last }
    let(:id) { audit.auditable_id }
    let(:field_name) { audit.field }
    let(:start_date) { Date.yesterday.to_s }
    let(:end_date) { Date.today.to_s }

    before do
      post '/audits', params: payload
    end

    context 'when query string includes params' do
      it 'should render recipes#show template' do
        get "/audits/#{id}?field=#{field_name}&start_date=#{start_date}&end_date=#{end_date}"
        parsed_response = JSON.parse(response.body)
        expect(parsed_response).to include(
          'field' => an_instance_of(String),
          'old' => an_instance_of(String),
          'new' => an_instance_of(String)
        )
      end
    end

    context 'when query start_date or end_date are not included in params' do
      it 'should render recipes#show template with date of today' do
        get "/audits/#{id}?field=#{field_name}"
        parsed_response = JSON.parse(response.body)
        expect(parsed_response).to include(
          'field' => an_instance_of(String),
          'old' => an_instance_of(String),
          'new' => an_instance_of(String)
        )
      end
    end

    context 'when query has only id as params' do
      it 'should render recipes#show template with date of today' do
        get "/audits/#{id}"
        parsed_response = JSON.parse(response.body)
        expect(parsed_response).to include(
          { 'status' => 422, 'error' => 'Please provide the necessary parameters!' }
        )
      end
    end
  end
end
