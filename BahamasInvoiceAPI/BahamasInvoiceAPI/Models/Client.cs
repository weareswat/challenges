using BahamasInvoiceAPI.Constants;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace BahamasInvoiceAPI.Models
{
    public class Client
    {
        [Key]
        public string FiscalId { get; set; }
        public string Name { get; set; }
        public string Email { get; set; }

        public virtual ICollection<Invoice> Invoices { get; set; }
    }

    public class ClientDto
    {
        [JsonProperty("fiscal_id")]
        public string FiscalId { get; set; }

        [JsonProperty("name")]
        public string Name { get; set; }

        [RegularExpression(RegexConstants.EMAIL)]
        [DataType(DataType.EmailAddress)]
        [JsonProperty("email")]
        public string Email { get; set; }
    }

    internal static class MapToDto
    {
        public static ClientDto ToDto(this Client c)
        {
            return new ClientDto
            {
                FiscalId = c.FiscalId,
                Name = c.Name,
                Email = c.Email 
            };
        }
    }
}
