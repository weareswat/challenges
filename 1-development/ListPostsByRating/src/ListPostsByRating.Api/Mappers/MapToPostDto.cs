using Api.DTOs;
using Api.Models;

namespace Api.Mappers
{
    internal static class MapToPostDto
    {
        public static PostDto ToPostDto(this Post post)
        {
            return new PostDto
            {
                DownVotes = post.DownVotes,
                Id = post.Id,
                UpVotes = post.UpVotes
            };
        }
    }
}
