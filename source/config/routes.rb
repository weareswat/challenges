Rails.application.routes.draw do
  root 'posts#index'
  resources :posts, only: [:index]

  namespace :api do
    namespace :v1 do
      resources :posts, only: [:index] do
        member do
          post :upvote
          post :downvote
        end
      end
    end
  end
end
