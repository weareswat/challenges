using BahamasInvoiceAPI.Models;
using System.Threading.Tasks;

namespace BahamasInvoiceAPI.Interfaces
{
    public interface IInvoiceService
    {
        public Task<ClientDto> RetrieveClientAsync(int id);

        public Task<StoreClientResponse> StoreClientAsync(int id, ClientDto client);
    }
}
