Rails.application.routes.draw do
  resources :posts, only: %i[index]
  put '/upvote/:id', to: 'posts#upvote', as: :post_upvote
  put '/downvote/:id', to: 'posts#downvote', as: :post_downvote
end
