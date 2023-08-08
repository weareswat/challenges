Rails.application.routes.draw do
  # Define your application routes per the DSL in https://guides.rubyonrails.org/routing.html

  # Defines the root path route ("/")
  # root "articles#index"

  get 'posts', to: 'posts#index'
  match 'upvote/:post_id', to: 'votes#upvote', via: [:put, :patch]
  match 'downvote/:post_id', to: 'votes#downvote', via: [:put, :patch]
end
