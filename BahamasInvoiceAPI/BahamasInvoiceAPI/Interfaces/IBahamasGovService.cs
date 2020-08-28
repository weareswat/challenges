using BahamasInvoiceAPI.Models;
using System.Threading.Tasks;

namespace BahamasInvoiceAPI.Interfaces
{
    public interface IBahamasGovService
    {
        public Task RegisterAsync(Invoice invoice);
    }
}
