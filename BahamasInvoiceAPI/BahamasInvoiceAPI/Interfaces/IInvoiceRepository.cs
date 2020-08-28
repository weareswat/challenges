using BahamasInvoiceAPI.Models;
using System.Threading.Tasks;

namespace BahamasInvoiceAPI.Interfaces
{
    public interface IInvoiceRepository
    {
        public Task<RetrieveClientResponse> RetrieveClientAsync(int id);

        public Task<StoreClientResponse> StoreClientAsync(int id, ClientDto client);
    }
}
