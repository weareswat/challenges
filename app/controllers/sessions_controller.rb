class SessionsController < ApplicationController
  def create
    @user = User.find_by(email: params[:email])

    if @user.authenticate(params[:password])
      session[:user_id] = @user.id
      render json: @user
    else
      render json: { error: 'Invalid email or password' }
    end
  end

  def logout
    session.clear if logged_in?

    render json: { success: true }
  end
end
