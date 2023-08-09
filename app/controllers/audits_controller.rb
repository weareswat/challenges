# frozen_string_literal: true

class AuditsController < ApplicationController
  def index
    query = if params.include?(:start_date && :end_date)
              Audit.created_at(params[:start_date], params[:end_date]).order_by_created_at
            else
              Audit.all.order_by_created_at
            end

    serialized_audit = FetchAuditService.new(audit: query).perform

    if serialized_audit.present?
      render json: serialized_audit, status: 200
    else
      render json: { status: 422, error: 'Please provide the necessary parameters!' }, status: :unprocessable_entity
    end
  rescue StandardError => e
    render json: { status: 422, exception: e.class, error: e }, status: :unprocessable_entity
  end

  def create
    raise 'InvalidObject' unless json_request?

    audit = AuditService.new(audit_changes: audit_params).perform

    if audit.present?
      render json: { status: 200, success: 'Audit created!' }, status: 200
    else
      render json: { status: :unprocessable_entity, error: 'Audit creation failed!' }, status: :unprocessable_entity
    end
  rescue StandardError => e
    render json: { status: 422, exception: e.class, error: e }, status: :unprocessable_entity
  end

  private

  def audit_params
    params.except(:_json, :audit, :controller, :action)
  end

  def json_request?
    request.content_type == 'application/json'
  end
end
