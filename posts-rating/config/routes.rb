Rails.application.routes.draw do
  root 'posts#index'

  # resources :votes, only: [:create] do
  #   member do
  #     post :increase_up_vote, path: 'upvote'
  #     post :increase_down_vote, path: 'downvote'
  #   end
  # end

  resources :posts
  post '/upvote/:post_id', to: 'votes#create', defaults: { vote_type: :up }, as: 'post_up_vote'
  post '/downvote/:post_id', to: 'votes#create', defaults: { vote_type: :down }, as: 'post_down_vote'
end
