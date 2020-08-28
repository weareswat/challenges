using BahamasInvoiceAPI.Interfaces;
using BahamasInvoiceAPI.Models;
using System.Net.Http;
using System.Threading.Tasks;

namespace BahamasInvoiceAPI.Services
{
    public class BahamasGovService : IBahamasGovService
    {
        private readonly HttpClient _httpClient;

        public BahamasGovService(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public async Task RegisterAsync(Invoice invoice)
        {
            string queryString = string.Format("register?id={0}&fiscal_id={1}&name={2}&email={3}",
                invoice.Id,
                invoice.Client.FiscalId,
                invoice.Client.Name,
                invoice.Client.Email);

            var request = new HttpRequestMessage(HttpMethod.Put, _httpClient.BaseAddress + queryString);

            var response = await _httpClient.SendAsync(request);

            response.EnsureSuccessStatusCode();
        }
    }
}
