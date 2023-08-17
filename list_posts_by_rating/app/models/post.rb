class Post < ApplicationRecord
    
    
    
    def score
        if number_of_votes > 0
            ((self[:upvotes].fdiv(number_of_votes))*10).round
        else
            0
        end
        
    end
 
    def number_of_votes
        self[:upvotes]+self[:downvotes]
    end
end
