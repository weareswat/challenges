using Newtonsoft.Json;
using System.ComponentModel.DataAnnotations;

namespace BahamasInvoiceAPI.Models
{
    public class Invoice
    {
        [Key]
        public int Id { get; set; }
        public string ClientId { get; set; }

        public virtual Client Client { get; set; }
    }
}
