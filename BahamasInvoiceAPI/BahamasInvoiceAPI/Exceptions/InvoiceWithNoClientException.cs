using System;
using System.Runtime.Serialization;

namespace BahamasInvoiceAPI.Exceptions
{
    [Serializable]
    public class InvoiceWithNoClientException : Exception
    {
        public InvoiceWithNoClientException() : base("This invoice has no client.")
        {
        }

        public InvoiceWithNoClientException(string message) : base(message)
        {
        }

        public InvoiceWithNoClientException(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected InvoiceWithNoClientException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}