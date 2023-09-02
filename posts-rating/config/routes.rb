Rails.application.routes.draw do
  resources :votes
  resources :posts, path: '/'
end
