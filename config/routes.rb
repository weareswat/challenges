Rails.application.routes.draw do
  namespace :api do 
    namespace :v1 do
      resources :post
    end
  end
end
