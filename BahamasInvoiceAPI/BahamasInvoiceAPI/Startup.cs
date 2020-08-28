using BahamasInvoiceAPI.Data;
using BahamasInvoiceAPI.Interfaces;
using BahamasInvoiceAPI.Repositories;
using BahamasInvoiceAPI.Services;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using System;

namespace BahamasInvoiceAPI
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllers()
                .AddNewtonsoftJson();            

            services.AddScoped<IInvoiceService, InvoiceService>();

            services.AddScoped<IInvoiceRepository, InvoiceRepository>()
                .AddDbContext<InvoiceDBContext>(options => 
                    options.UseInMemoryDatabase(databaseName: "InvoiceDB"));

            services.AddHttpClient<IBahamasGovService,BahamasGovService>(c =>
            {
                c.BaseAddress = new Uri("https://localhost:44379/api/");
            });

            services.AddSwaggerGen();
            services.AddSwaggerGenNewtonsoftSupport();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });

            // Enable middleware to serve generated Swagger as a JSON endpoint.
            app.UseSwagger();

            // Enable middleware to serve swagger-ui (HTML, JS, CSS, etc.),
            // specifying the Swagger JSON endpoint.
            app.UseSwaggerUI(c =>
            {
                c.SwaggerEndpoint("/swagger/v1/swagger.json", "Bahamas Invoice API V1");
                c.RoutePrefix = string.Empty;
                c.DefaultModelsExpandDepth(-1);
            });
        }
    }
}
