class RegistrationsController < ApplicationController
  def create
    @user = User.new(name: params[:name], email: params[:email], password: params[:password])

    if @user.save
      session[:user_id] = @user.id
      render json: @user
    else
      render json: { error: @user.errors }
    end
  end
end
