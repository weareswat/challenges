Rails.application.routes.draw do
  root "articles#index"
  resources :articles
  get "/articles/:id/upvote", to: "articles#upvote", as: 'upvote_article'
  get "/articles/:id/downvote", to: "articles#downvote", as: 'downvote_article'
end
