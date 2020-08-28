using System;
using System.Runtime.Serialization;

namespace BahamasInvoiceAPI.Exceptions
{
    [Serializable]
    public class InvoiceAlreadyHasClientException : Exception
    {
        public InvoiceAlreadyHasClientException() : base("Invoice already has a client.")
        {
        }

        public InvoiceAlreadyHasClientException(string message) : base(message)
        {
        }

        public InvoiceAlreadyHasClientException(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected InvoiceAlreadyHasClientException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}