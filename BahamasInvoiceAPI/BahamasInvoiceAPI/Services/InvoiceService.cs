using BahamasInvoiceAPI.Interfaces;
using BahamasInvoiceAPI.Models;
using System.Threading.Tasks;

namespace BahamasInvoiceAPI.Services
{
    public class InvoiceService : IInvoiceService
    {
        private readonly IInvoiceRepository _invoiceRepository;
        private readonly IBahamasGovService _bahamasGovService;

        public InvoiceService(IInvoiceRepository invoiceRepository, IBahamasGovService bahamasGovService)
        {
            _invoiceRepository = invoiceRepository;
            _bahamasGovService = bahamasGovService;
        }

        public async Task<ClientDto> RetrieveClientAsync(int id)
        {
            var clientResult = await _invoiceRepository.RetrieveClientAsync(id);

            return clientResult.Client.ToDto();
        }

        public async Task<StoreClientResponse> StoreClientAsync(int id, ClientDto client)
        {
            var invoiceResult = await _invoiceRepository.StoreClientAsync(id, client);

            await _bahamasGovService.RegisterAsync(invoiceResult.Invoice);

            return invoiceResult;
        }
    }
}
