using Api.Exceptions;
using Api.Models;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Api.Data
{
    public interface IPostsRepository
    {
        Task<IEnumerable<Post>> GetAllAsync();
        Task<Post> GetByIdAsync(int id);
        Task<Post> UpdateAsync(Post updated);
    }

    public class PostsRepository : IPostsRepository
    {
        private static readonly List<Post> _posts = new List<Post>();

        static PostsRepository()
        {
            // Seed in memory database with 3 posts
            _posts.AddRange(new Post[]
            {
                new Post { Id = 1 },
                new Post { Id = 2 },
                new Post { Id = 3 }
            });
        }

        public async Task<IEnumerable<Post>> GetAllAsync()
        {
            return await Task.FromResult(_posts);
        }

        public async Task<Post> GetByIdAsync(int id)
        {
            var postFound = _posts.SingleOrDefault(post => post.Id == id);
            if(postFound is object)
            {
                return await Task.FromResult(postFound);
            }
            throw new PostNotFoundException();
        }

        public async Task<Post> UpdateAsync(Post updated)
        {
            var postFound = _posts.SingleOrDefault(post => post.Id == updated.Id);
            postFound.DownVotes = updated.DownVotes;
            postFound.UpVotes = updated.UpVotes;
            return await Task.FromResult(postFound);
        }
    }
}
