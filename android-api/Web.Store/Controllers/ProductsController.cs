using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using Web.Store.Data;
using Web.Store.Data.Entities;
using Web.Store.Models;

namespace Web.Store.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProductsController : ControllerBase
    {
        private readonly EFAppContext _context;
        public ProductsController(EFAppContext context)
        {
            _context = context;
        }
        [Authorize(Roles = "user")]
        [HttpGet("all")]
        public async Task<IActionResult> GetAll()
        {
            Thread.Sleep(5000);
            var list = await _context.Products
                .Select(x=> new ProductItemVM
                {
                    Id=x.Id,
                    Name=x.Name,
                    Price=x.Price,
                    Image=x.ProductImages
                        .Select(x=>x.Name)
                        .FirstOrDefault() ?? "noimage.jpg"
                })
                .ToListAsync();
            return Ok(list);
        }

        [HttpGet("get/{id}")]
        public async Task<IActionResult> GetById(int id)
        {
            var list = await _context.ProductImages
                .Select(x=>new ProductInfoVM 
                {
                    Id=x.Id,
                    Path="/images/"+x.Name
                })
                .ToListAsync();
            return Ok(list);
        }


        [HttpPost("add")]
        public async Task<IActionResult> Add(ProductAddVM model)
        {
            await _context.Products.AddAsync(new Product
            {
                Name=model.Name,
                Price=model.Price
            });
            _context.SaveChanges();   
            return Ok();
        }

    }
}
