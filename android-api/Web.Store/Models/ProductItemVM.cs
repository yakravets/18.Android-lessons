using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Web.Store.Models
{
    public class ProductItemVM
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public decimal Price { get; set; }
        public string Image { get; set; }
    }
    public class ProductAddVM
    {
        public string Name { get; set; }
        public decimal Price { get; set; }
    }
}
