using Api.Data;
using Api.Models;
using Api.Services;

namespace Microsoft.Extensions.DependencyInjection
{
    internal static class ServicesExtensions
    {
        public static IServiceCollection AddServices(this IServiceCollection services)
        {
            return services.AddScoped<IPostService, PostService>()
                .AddSingleton<IPostsRepository, PostsRepository>(
                // Seed in memory database with 3 posts
                provider =>
                {
                    var repo = new PostsRepository();
                    repo.Seed(
                    new Post[]
                    {
                        new Post { Id = 1 },
                        new Post { Id = 2 },
                        new Post { Id = 3 }
                    });
                    return repo;
                });
        }
    }
}
