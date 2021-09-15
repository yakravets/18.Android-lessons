using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WebStoreAPI.Data.Entities
{
    [Table("products")]
    public class Product
    {
        [Key]
        public int Id { get; set; }
        
        [Required, StringLength(255)]
        public string Name { get; set; }

        [Required]
        public decimal Price { get; set; }

        public virtual ICollection<ProductImage> ProductImages { get; set; }
    }
}
