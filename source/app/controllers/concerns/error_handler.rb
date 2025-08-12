module ErrorHandler
  extend ActiveSupport::Concern

  included do
    rescue_from StandardError, with: :handle_standard_error
    rescue_from ActiveRecord::RecordNotFound, with: :handle_not_found
    rescue_from ActiveRecord::RecordInvalid, with: :handle_validation_error
    rescue_from ActionController::ParameterMissing, with: :handle_parameter_missing
    rescue_from ActionController::UnpermittedParameters, with: :handle_unpermitted_parameters
  end

  private

  def handle_standard_error(exception)
    log_error(exception)
    
    respond_to do |format|
      format.html { render 'errors/internal_server_error', status: :internal_server_error }
      format.json { render json: { error: 'Internal server error' }, status: :internal_server_error }
    end
  end

  def handle_not_found(exception)
    log_error(exception)
    
    respond_to do |format|
      format.html { render 'errors/not_found', status: :not_found }
      format.json { render json: { error: 'Resource not found' }, status: :not_found }
    end
  end

  def handle_validation_error(exception)
    log_error(exception)
    
    respond_to do |format|
      format.html { 
        flash[:error] = "Validation failed: #{exception.record.errors.full_messages.join(', ')}"
        redirect_back(fallback_location: root_path)
      }
      format.json { 
        render json: { 
          error: 'Validation failed',
          details: exception.record.errors.full_messages 
        }, status: :unprocessable_entity 
      }
    end
  end

  def handle_parameter_missing(exception)
    log_error(exception)
    
    respond_to do |format|
      format.html { render 'errors/bad_request', status: :bad_request }
      format.json { render json: { error: "Missing parameter: #{exception.param}" }, status: :bad_request }
    end
  end

  def handle_unpermitted_parameters(exception)
    log_error(exception)
    
    respond_to do |format|
      format.html { render 'errors/bad_request', status: :bad_request }
      format.json { render json: { error: "Unpermitted parameters: #{exception.params}" }, status: :bad_request }
    end
  end

  def log_error(exception)
    Rails.logger.error "Error: #{exception.class} - #{exception.message}"
    Rails.logger.error exception.backtrace.join("\n") if Rails.env.development?
  end
end
