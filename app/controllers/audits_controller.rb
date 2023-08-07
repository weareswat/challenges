# frozen_string_literal: true

class AuditsController < ApplicationController
  def show
    query = Audit.where(auditable_id: params[:id])
                 .where("created_at::date >= '#{params[:start_date] || Date.today}'
                        AND created_at::date <= '#{params[:end_date] || Date.today}'")
                 .where(field: params[:field])
                 .order(created_at: :asc)

    serialized_audit = FetchAuditService.new(audit: query).perform

    if serialized_audit.present?
      render json: serialized_audit, status: 200
    else
      render json: { status: 422, error: 'Please provide the necessary parameters!' }, status: :unprocessable_entity
    end
  end

  def create
    audit = AuditService.new(
      model: audit_params[:model],
      old_changes: audit_params[:old],
      new_changes: audit_params[:new]
    ).perform

    if audit
      render json: { status: 200, success: 'Audit created!' }, status: 200
    else
      render json: { status: :unprocessable_entity, success: 'Audit creation failed!' }, status: :unprocessable_entity
    end
  rescue ActionController::ParameterMissing
    render json: { status: 422, error: 'Please provide the necessary parameters!' }, status: :unprocessable_entity
  end

  private

  def audit_params
    params.require(:audit).permit(:model, old: {}, new: {})
  end
end
