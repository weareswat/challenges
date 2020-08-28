using BahamasInvoiceAPI.Models;
using Microsoft.AspNetCore.Mvc;

namespace BahamasInvoiceAPI.Controllers
{
    [Route("api/")]
    [ApiController]
    public class BahamasGovAPIController : ControllerBase
    {
        /// <summary>
        /// Register Mock Service - Simulate an external call to bahamas.gov/register
        /// </summary>
        /// <param name="registerRequest"></param>
        /// <returns></returns>
        [HttpPut("register")]
        public ActionResult Register([FromQuery] RegisterRequest registerRequest)
        {
            return base.Ok();
        }
    }
}
