module Api
  module V1
    class UsersController < ApplicationController
      def index
        users = User.all.order(:id)

        render status: :ok, json: { users: }
      end

      def create
        user = User.new(user_params)

        if user.save
          render status: :created, json: { user: }
        else
          render status: :unprocessable_entity, json: { user: user.errors.full_messages }
        end
      end

      private

      def user_params
        params.require(:user).permit(:name)
      end
    end
  end
end
