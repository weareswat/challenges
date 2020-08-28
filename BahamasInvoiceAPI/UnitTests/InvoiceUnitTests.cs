using BahamasInvoiceAPI.Data;
using BahamasInvoiceAPI.Models;
using BahamasInvoiceAPI.Repositories;
using BahamasInvoiceAPI.Exceptions;
using Microsoft.EntityFrameworkCore;
using Xunit;
using System.Linq;

namespace UnitTests
{
    public class InvoiceUnitTests
    {
        private InvoiceRepository _repository { get; set; }

        public InvoiceUnitTests()
        {
            var options = new DbContextOptionsBuilder<InvoiceDBContext>()
                .UseInMemoryDatabase(databaseName: "InvoiceDB")
                .Options;

            var context = new InvoiceDBContext(options);

            #region seed data
            if (!context.Clients.Any() && !context.Invoices.Any())
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
            #endregion

            _repository = new InvoiceRepository(context);
        }

        [Fact]
        public void RetrieveClient_ClientFound()
        {
            var client = _repository.RetrieveClientAsync(1000);
            Assert.NotNull(client);
        }

        [Fact]
        public void RetrieveClient_InvoiceWithNoClient()
        {
            Assert.ThrowsAsync<InvoiceWithNoClientException>(() => 
                _repository.RetrieveClientAsync(1002));
        }

        [Fact]
        public void RetrieveClient_InvoiceNotFound()
        {
            Assert.ThrowsAsync<InvoiceNotFoundException>(() =>
                _repository.RetrieveClientAsync(1002));
        }

        [Fact]
        public void StoreClient_ReturnOK()
        {
            var client = new ClientDto
            {
                Name = "João Doe",
                Email = "joao.doe@gmail.com",
                FiscalId = "211000000"
            };

            var result = _repository.StoreClientAsync(1002, client);
            Assert.NotNull(result);
        }

        [Fact]
        public void StoreClient_InvoiceAlreadyHasClient()
        {
            var client = new ClientDto
            {
                Name = "João Doe",
                Email = "joao.doe@gmail.com",
                FiscalId = "211000000"
            };

            Assert.ThrowsAsync<InvoiceAlreadyHasClientException>(() =>
                _repository.StoreClientAsync(1001, client));
        }

        [Fact]
        public void StoreClient_InvoiceNotFound()
        {
            var client = new ClientDto
            {
                Name = "João Doe",
                Email = "joao.doe@gmail.com",
                FiscalId = "211000000"
            };

            Assert.ThrowsAsync<InvoiceNotFoundException>(() =>
                _repository.StoreClientAsync(1004, client));
        }
    }
}
