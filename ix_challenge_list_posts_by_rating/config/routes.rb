Rails.application.routes.draw do
  resources :posts, only: [:create, :index] do
    member do
      put 'upvote'
      put 'downvote'
    end
  end
end