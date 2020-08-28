using BahamasInvoiceAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace BahamasInvoiceAPI.Data
{
    public class InvoiceDBContext : DbContext
    {
        public DbSet<Invoice> Invoices { get; set; }
        public DbSet<Client> Clients { get; set; }

        public InvoiceDBContext(DbContextOptions<InvoiceDBContext> options) : base(options)
        {
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Invoice>()
                .HasOne(p => p.Client)
                .WithMany(p => p.Invoices)
                .HasForeignKey(p => p.ClientId);
        }
    }
}
