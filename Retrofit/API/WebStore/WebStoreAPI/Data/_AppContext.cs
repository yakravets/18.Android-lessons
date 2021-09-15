using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebStoreAPI.Data.Entities;

namespace WebStoreAPI.Data
{
    public class _AppContext: DbContext
    {
        public DbSet<Product> products { get; set; }
        public DbSet<ProductImage> productImages { get; set; }

        public _AppContext(DbContextOptions<_AppContext> options) :
            base(options)
        { }       
    }
}
