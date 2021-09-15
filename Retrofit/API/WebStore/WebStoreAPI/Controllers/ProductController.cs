using AutoMapper;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebStoreAPI.Data.Entities;
using WebStoreAPI.Repositories;
using WebStoreAPI.ViewModel;

namespace WebStoreAPI.Controllers
{
   
    [Produces("application/json")]
    [Consumes("application/json")]
    [ProducesResponseType(StatusCodes.Status200OK)]
    [ProducesResponseType(StatusCodes.Status404NotFound)]
    [ApiController]
    [Route("api/product/v1")]
    public class ProductController : ControllerBase
    {
        private readonly IRepository<Product> repository;
        private readonly IMapper _mapper;

        public ProductController(IRepository<Product> repository, IMapper mapper)
        {
            this.repository = repository;
            _mapper = mapper;
        }

        /// <summary>
        /// Get list all products
        /// </summary>
        /// <returns>Collection products</returns>
        /// 
        [HttpGet]
        [Route("all")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public IActionResult All()
        {
            var products = repository.GetAll().ToList();
            List<ProductViewModel> listProductModelView = _mapper.Map<List<ProductViewModel>>(products);
            if (products.Count == 0)
            {
                return NotFound(listProductModelView);
            }

            return Ok(listProductModelView);
        }

        /// <summary>
        /// Create new product
        /// </summary>
        /// <param name="viewModel"></param>
        /// <returns>Product DTO if success or error</returns>
        /// <response code="200">Success</response>
        /// <response code="400">Bad request</response>
        [HttpPost]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        public async Task<IActionResult> AddAsync(ProductViewModel viewModel)
        {
            if (viewModel.Name == null)
            {
                return BadRequest("Name is required.");
            }

            if (viewModel.Price == 0)
            {
                return BadRequest("Price equal 0");
            }

            var product = _mapper.Map<Product>(viewModel);
            await repository.CreateAsync(product);
            await repository.SaveAsync();

            return Ok(viewModel);
        }
    }
}
