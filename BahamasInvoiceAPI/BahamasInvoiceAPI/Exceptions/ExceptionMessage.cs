using Newtonsoft.Json;

namespace BahamasInvoiceAPI.Exceptions
{
    public class ExceptionMessage
    {
        [JsonProperty("message")]
        public string Message { get; set; }
    }
}
