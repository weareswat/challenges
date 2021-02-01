Rails.application.routes.draw do
  namespace :api do
    namespace :v1 do
      resources :posts, only: [:index] do
        member do
          resources :upvotes, only: :create
          resources :downvotes, only: :create
        end
      end
    end
  end
  # For details on the DSL available within this file, see https://guides.rubyonrails.org/routing.html
end
