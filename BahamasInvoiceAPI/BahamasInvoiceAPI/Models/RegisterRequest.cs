using Newtonsoft.Json;

namespace BahamasInvoiceAPI.Models
{
    public class RegisterRequest
    {
        [JsonProperty("id")]
        public int Id { get; set; }

        [JsonProperty("fiscal_id")]
        public string FiscalId { get; set; }

        [JsonProperty("name")]
        public string Name { get; set; }

        [JsonProperty("email")]
        public string Email { get; set; }
    }
}
