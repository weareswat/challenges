using System;
using System.Runtime.Serialization;

namespace BahamasInvoiceAPI.Exceptions
{
    [Serializable]
    public class InvoiceNotFoundException : Exception
    {
        public InvoiceNotFoundException() : base("Invoice not found.")
        {
        }

        public InvoiceNotFoundException(string message) : base(message)
        {
        }

        public InvoiceNotFoundException(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected InvoiceNotFoundException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}