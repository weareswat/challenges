Rails.application.routes.draw do
  root 'posts#index'

  resources :votes
  resources :posts
end
