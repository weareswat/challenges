def generate_data_for_post
  {
    title: Faker::Lorem.words(number: rand(2..6)).join(' '),
    content: Faker::Lorem.paragraphs(number: rand(2..5)).join(' ')
  }
end

post_one = Post.create(generate_data_for_post)
post_two = Post.create(generate_data_for_post)

60.times do
  post_one.rate(Vote.new(username: 'guilherme', vote_type: :up))
end

40.times do
  post_one.rate(Vote.new(username: 'guilherme', vote_type: :down))
end

6.times do
  post_two.rate(Vote.new(username: 'guilherme', vote_type: :up))
end

4.times do
  post_two.rate(Vote.new(username: 'guilherme', vote_type: :down))
end
