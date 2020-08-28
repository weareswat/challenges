using BahamasInvoiceAPI.Data;
using BahamasInvoiceAPI.Exceptions;
using BahamasInvoiceAPI.Interfaces;
using BahamasInvoiceAPI.Models;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace BahamasInvoiceAPI.Repositories
{
    public class InvoiceRepository : IInvoiceRepository
    {
        private readonly InvoiceDBContext _context;

        public InvoiceRepository(InvoiceDBContext context)
        {
            _context = context;
        }

        public async Task<StoreClientResponse> StoreClientAsync(int id, ClientDto client)
        {
            Invoice invoice = GetInvoice(id);

            if (invoice.Client != null)
                throw new InvoiceAlreadyHasClientException();

            invoice.Client = new Client
            { 
                FiscalId = client.FiscalId,
                Email = client.Email,
                Name = client.Name
            };

            _context.SaveChanges();

            return await Task.FromResult(new StoreClientResponse 
            { 
                Invoice = invoice 
            });
        }

        public async Task<RetrieveClientResponse> RetrieveClientAsync(int id)
        {
            Invoice invoice = GetInvoice(id);

            if(invoice.Client == null)
                throw new InvoiceWithNoClientException();

            return await Task.FromResult(new RetrieveClientResponse 
            { 
                Client = invoice.Client
            });
        }

        private Invoice GetInvoice(int id)
        {
            Invoice invoice = _context.Invoices
                .Include(x => x.Client)
                .FirstOrDefault(x => x.Id == id);

            if (invoice == null)
                throw new InvoiceNotFoundException();

            return invoice;
        }
    }
}
