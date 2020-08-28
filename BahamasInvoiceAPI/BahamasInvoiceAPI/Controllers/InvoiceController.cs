using System;
using System.Threading.Tasks;
using BahamasInvoiceAPI.Exceptions;
using BahamasInvoiceAPI.Interfaces;
using BahamasInvoiceAPI.Models;
using Microsoft.AspNetCore.Mvc;

namespace BahamasInvoiceAPI.Controllers
{
    [Route("api/")]
    [ApiController]
    public class InvoiceController : ControllerBase
    {
        private readonly IInvoiceService _invoiceService;

        public InvoiceController(IInvoiceService invoiceService)
        {
            _invoiceService = invoiceService;
        }

        /// <summary>
        /// Retrieves the client associated to an invoice
        /// </summary>
        /// <param name="id">Invoice identifier</param>
        [HttpGet("retrieve-bahamas-client/{id}")]
        public async Task<ActionResult<ClientDto>> RetrieveClient(int id)
        {
            try
            {
                var clientResult = await _invoiceService.RetrieveClientAsync(id);
                return base.Ok(clientResult);
            }
            catch (InvoiceNotFoundException e)
            {
                return base.BadRequest(new ExceptionMessage { Message = e.Message });
            }
            catch (InvoiceWithNoClientException e)
            {
                return base.BadRequest(new ExceptionMessage { Message = e.Message });
            }
            catch (Exception)
            {
                return base.Problem();
            }
        }

        /// <summary>
        /// Associate a client to an invoice
        /// </summary>
        /// <param name="id">Invoice identifier</param>
        /// <param name="input">Client personal information to be associated to the invoice</param>
        [HttpPut("store-bahamas-client/{id}")]
        public async Task<ActionResult> StoreClient(int id, [FromQuery] ClientDto input)
        {
            try
            {
                var result = await _invoiceService.StoreClientAsync(id, input);
                return base.Ok(result);
            }
            catch (InvoiceAlreadyHasClientException e)
            {
                return base.BadRequest(new ExceptionMessage { Message = e.Message });
            }
            catch (Exception)
            {
                return base.Problem();
            }
        }
    }
}
