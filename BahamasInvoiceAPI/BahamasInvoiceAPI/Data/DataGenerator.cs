using BahamasInvoiceAPI.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using System;

namespace BahamasInvoiceAPI.Data
{
    public class DataGenerator
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new InvoiceDBContext(serviceProvider.GetRequiredService<DbContextOptions<InvoiceDBContext>>()))
            {
                context.Clients.AddRange(
                    new Client { Name = "John Doe", Email = "john.doe@gmail.com", FiscalId = "100287441" },
                    new Client { Name = "Jane Doe", Email = "jane.doe@gmail.com", FiscalId = "100287442" });

                context.Invoices.AddRange(
                    new Invoice() { Id = 1000, ClientId = "100287441" },
                    new Invoice() { Id = 1001, ClientId = "100287442" },
                    new Invoice() { Id = 1002 },
                    new Invoice() { Id = 1003 });

                context.SaveChanges();
            }
        }
    }
}
