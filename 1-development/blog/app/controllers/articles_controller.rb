class ArticlesController < ApplicationController
  def index
    @articles = Article.all.order("rating desc, votes desc, created_at desc")
  end

  def show
    @article = Article.find(params[:id])
  end

  def new
    @article = Article.new
  end

  def create
    @article = Article.new(article_params)

    # Not sure if this initialisation should be done here.
    @article.upvotes = 0
    @article.downvotes = 0
    @article.votes = 0
    @article.rating = 0
    
    if @article.save
      redirect_to @article
    else
      render :new
    end
  end

  def edit
    @article = Article.find(params[:id])
  end

  def update
    @article = Article.find(params[:id])

    if @article.update(article_params)
      redirect_to @article
    else
      render :edit
    end
  end

  def upvote
    @article = Article.find(params[:id])
    @article.upvote
    if @article.save
      redirect_to @article
    else
      raise "Cannot upvote."
    end
  end

  def downvote
    @article = Article.find(params[:id])
    @article.downvote
    if @article.save
      redirect_to @article
    else
      raise "Cannot downvote."
    end
  end

  def destroy
    @article = Article.find(params[:id])
    @article.destroy

    redirect_to root_path
  end

  private
    def article_params
      params.require(:article).permit(:title, :body)
    end
end
