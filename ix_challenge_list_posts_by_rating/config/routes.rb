Rails.application.routes.draw do
  post '/posts', to: 'posts#create'
  put '/upvote/:id', to: 'posts#upvote'
  put '/downvote/:id', to: 'posts#downvote'
  get '/posts', to: 'posts#index'
end
