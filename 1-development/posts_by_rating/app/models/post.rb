class Post < ApplicationRecord
  after_save :update_rate, if: :up_down_changed?

  default_scope { order(assessment_rate: :desc, up: :desc) }

  # def increment(attr)
  #   self[attr] += 1
  #   self.save!
  # end

  private

  def up_down_changed?
    saved_change_to_up? || saved_change_to_down?
  end

  def update_rate
    total_votes = up + down
    percent = (up.to_f / total_votes) * 100
    update_column(:assessment_rate, percent)
  end

end
