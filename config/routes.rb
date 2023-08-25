Rails.application.routes.draw do
  namespace :api do 
    namespace :v1 do
      resources :posts, only: [:index, :create, :update]

      post '/upvote/:post_id', to: 'posts#vote_up', as: :vote_up
      post '/downvote/:post_id', to: 'posts#vote_down', as: :vote_down 

      resources :users, only: [:index, :create]
    end
  end
end
