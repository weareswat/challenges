class Post < ApplicationRecord
  # Validations with custom error messages
  validates :title, presence: { message: "Title cannot be blank" }
  validates :upvotes, presence: { message: "Upvotes count is required" }, 
                      numericality: { 
                        greater_than_or_equal_to: 0, 
                        message: "Upvotes must be a non-negative number" 
                      }
  validates :downvotes, presence: { message: "Downvotes count is required" }, 
                        numericality: { 
                          greater_than_or_equal_to: 0, 
                          message: "Downvotes must be a non-negative number" 
                        }

  # Orders posts by Reddit-style logarithmic score (descending)
  scope :ordered_by_score, lambda {
    # Use a simpler approach that can be reversed
    order(Arel.sql("#{PostScoringService.logarithmic_score_sql} DESC"))
  }

  # Alternative method for ordering that can be reversed
  def self.ordered_by_score_reversible
    all.sort_by { |post| -post.score }
  end

  # Delegate scoring methods to the service
  def score
    PostScoringService.calculate_score(self)
  end

  def total_votes
    PostScoringService.new.total_votes(self)
  end

end


