using Newtonsoft.Json;

namespace BahamasInvoiceAPI.Models
{
    public class StoreClientResponse
    {
        [JsonProperty("invoice")]
        public Invoice Invoice { get; set; }
    }
}