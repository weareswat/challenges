Rails.application.routes.draw do
  get '/posts', to: 'posts#index'
  post '/posts', to: 'posts#create'
  put '/posts/:id', to: 'posts#update'
  put '/upvote/:id', to: 'posts#upvote'
  put '/downvote/:id', to: 'posts#downvote'

  get '/login', to: 'sessions#create'
  get '/logout', to: 'sessions#logout'
  post '/signup', to: 'registrations#create'
end
