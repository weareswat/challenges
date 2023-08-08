class ApplicationController < ActionController::API
  include ExceptionHandler  
  before_action :check_user, except: [:index]

  private

    def check_user
      if !request.env["HTTP_USER_ID"].present?
        render_message
      else
        @current_user_id = request.env["HTTP_USER_ID"]
      end
    end
  
    def render_message
      render json: { message: "missing the USER_ID in the header" }
    end
end
