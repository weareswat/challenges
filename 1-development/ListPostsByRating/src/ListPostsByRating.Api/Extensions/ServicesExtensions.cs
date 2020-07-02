using Api.Data;
using Api.Services;

namespace Microsoft.Extensions.DependencyInjection
{
    internal static class ServicesExtensions
    {
        public static IServiceCollection AddServices(this IServiceCollection services)
        {
            return services.AddScoped<IPostService, PostService>()
                .AddSingleton<IPostsRepository, PostsRepository>();
        }
    }
}
